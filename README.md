# cloud-computing-com4519
### Notes on Building
The project cannot be built from the root.
Individual subprojects must be built with `gradle <subproject>:<task>` e.g. `gradle auth:assemble` will build the `auth` project into a thin war file.
Projects can be run locally with the `bootRun` task.
### Notes on deployment
Any dependencies should be deployed to the tomcat libs folder via scp, as the gradle project builds thin war files due to the heavily constrained memory on the provided VM.
