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

/**
 * @author Andres Almiray
 */
class QuartzGriffonPlugin {
    // the plugin version
    String version = '0.2'
    // the version or versions of Griffon the plugin is designed for
    String griffonVersion = '0.9.5 > *'
    // the other plugins this plugin depends on
    Map dependsOn = [spring: '0.9']
    // resources that are included in plugin packaging
    List pluginIncludes = []
    // the plugin license
    String license = 'Apache Software License 2.0'
    // Toolkit compatibility. No value means compatible with all
    // Valid values are: swing, javafx, swt, pivot, gtk
    List toolkits = []
    // Platform compatibility. No value means compatible with all
    // Valid values are:
    // linux, linux64, windows, windows64, macosx, macosx64, solaris
    List platforms = []
    // URL where documentation can be found
    String documentation = ''
    // URL where source can be found
    String source = 'https://github.com/griffon/griffon-quartz-plugin'

    List authors = [
        [
            name: 'Andres Almiray',
            email: 'aalmiray@yahoo.com'
        ]
    ]
    String title = 'Job scheduling via Quartz'
    // accepts Markdown syntax. See http://daringfireball.net/projects/markdown/ for details
    String description = '''
The Quartz plugin allows your Griffon application to schedule jobs to be executed using a specified interval or
cron expression. The underlying system uses the [Quartz Enterprise Job Scheduler][1] configured via Spring, but
is made simpler by the coding by convention paradigm.
This is a direct port of the [Quartz][2] for [Grails][3]. Original plugin made by Sergey Nebolsin.

Usage
-----

This plugin provides the same features as it Grails counterpart except GORM integration.

Scripts
-------

 * **create-job** - creates a new Job class in griffon-app/jobs

[1]: http://www.quartz-scheduler.org
[2]: http://grails.org/plugin/quartz
[3]: http://grails.org
'''
}