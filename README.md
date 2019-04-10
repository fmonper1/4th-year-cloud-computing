# cloud-computing-com4519
### Notes on Building
The project cannot be built from the root.
Individual projects must be built with `gradle <subproject>:<task>` e.g. `gradle auth:assemble` will build the `auth` project into a thin war file.
Projects can be run locally with the `bootRun` task.
