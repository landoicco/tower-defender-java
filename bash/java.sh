#!/bin/bash

######################################################
# All Java-related commands (Compiling and packaging)
######################################################

# # As a design principle, we define a method for each module

# licaza.tdefender.commons
build_commons() {
    javac \
        --module-path src/mods \
        -d src/commons/target \
        src/commons/licaza/tdefender/commons/objects/**.java \
        src/commons/licaza/tdefender/commons/helpers/**.java \
        src/commons/module-info.java \

    cp -r src/res/** src/commons/target/

    jar -cvf src/mods/licaza.tdefender.commons.jar -C src/commons/target .
}

# licaza.tdefender.core
build_core() {
    javac \
        --module-path src/mods -d src/core/target \
        src/core/licaza/tdefender/core/enemies/**.java \
        src/core/licaza/tdefender/core/scenes/**.java \
        src/core/licaza/tdefender/core/ui/**.java \
        src/core/licaza/tdefender/core/managers/**.java \
        src/core/licaza/tdefender/core/inputs/**.java \
        src/core/licaza/tdefender/core/main/**.java \
        src/core/module-info.java \

    jar \
        -cvfe src/mods/licaza.tdefender.core.jar \
        licaza.tdefender.core.main.Game \
        -C src/core/target .
}
    