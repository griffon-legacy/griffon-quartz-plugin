/*
 * Copyright 2010-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.codehaus.griffon.compiler.support;

import org.codehaus.groovy.ast.*;
import org.codehaus.groovy.ast.expr.*;
import org.codehaus.groovy.ast.stmt.*;
import org.codehaus.griffon.ast.GriffonASTUtils;
import org.codehaus.griffon.compiler.GriffonCompilerContext;
import org.codehaus.griffon.compiler.SourceUnitCollector;
import org.codehaus.groovy.control.CompilePhase;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.GroovyASTTransformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import griffon.plugins.quartz.GriffonJob;
import griffon.plugins.quartz.GriffonStatefulJob;
import griffon.plugins.quartz.GriffonJobClass;
import org.codehaus.griffon.runtime.quartz.AbstractGriffonJob;
import org.codehaus.griffon.runtime.quartz.AbstractGriffonStatefulJob;

/**
 * Handles generation of code for Quartz jobs.<p/>
 *
 * @author Andres Almiray 
 */
@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
public class GriffonJobASTTransformation extends GriffonArtifactASTTransformation {
    private static final Logger LOG = LoggerFactory.getLogger(GriffonJobASTTransformation.class);
    private static final String ARTIFACT_PATH = "jobs";
    private static final ClassNode GRIFFON_JOB_CLASS = ClassHelper.makeWithoutCaching(GriffonJob.class);
    private static final ClassNode GRIFFON_JOB_STATEFUL_CLASS = ClassHelper.makeWithoutCaching(GriffonStatefulJob.class);
    private static final ClassNode ABSTRACT_GRIFFON_JOB_CLASS = ClassHelper.makeWithoutCaching(AbstractGriffonJob.class);
    private static final ClassNode ABSTRACT_GRIFFON_STATEFUL_JOB_CLASS = ClassHelper.makeWithoutCaching(AbstractGriffonStatefulJob.class);

    public static boolean isGriffonJobArtifact(ClassNode classNode, SourceUnit source) {
        if (classNode == null || source == null) return false;
        return ARTIFACT_PATH.equals(GriffonCompilerContext.getArtifactPath(source)) && classNode.getName().endsWith(GriffonJobClass.TRAILING);
    }

    protected void transform(ClassNode classNode, SourceUnit source, String artifactPath) {
        if (!isGriffonJobArtifact(classNode, source)) return;
        doTransform(classNode);
    }

    private void doTransform(ClassNode classNode) {
        if(ClassHelper.OBJECT_TYPE.equals(classNode.getSuperClass())) {
            ClassNode superClass = ABSTRACT_GRIFFON_JOB_CLASS;
            // get concurrent property
            PropertyNode concurrent = classNode.getProperty("concurrent");
            // if concurrent undef || false -> AbstractGriffonJob else -> AbstractGriffonStatefulJob
            if(concurrent != null) {
                Expression expr = concurrent.getInitialExpression();
                if(expr instanceof ConstantExpression) {
                    ConstantExpression value = (ConstantExpression) expr;
                    if(Boolean.TRUE.equals(value.getValue())) superClass = ABSTRACT_GRIFFON_STATEFUL_JOB_CLASS;
                }
                // classNode.getProperties().remove(concurrent);
            }
            classNode.setSuperClass(superClass);
        } else if(!classNode.implementsInterface(GRIFFON_JOB_CLASS)){
            // not supported!!!
            throw new RuntimeException("Custom super classes are not supported for artifacts of type "+GriffonJobClass.TYPE+".");
        }
    }
}