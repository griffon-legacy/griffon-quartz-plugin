
Job scheduling via Quartz
-------------------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/quartz](http://artifacts.griffon-framework.org/plugin/quartz)


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

