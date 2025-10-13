#!/bin/bash

# # As design principle, the formal name of the module to be build
# # needs to be defined on a variable

COMMONS_MODULE=licaza.tdefender.commons
CORE_MODULE=licaza.tdefender.core
 
COMMONS_PATH=src/commons/licaza/tdefender/commons
CORE_PATH=src/core/licaza/tdefender/core


######################################################
# All Java-related commands (Compiling and packaging)
######################################################

# # For design compliance, we define a method for each module

# licaza.tdefender.commons
build_commons() {
    javac \
        --module-path src/mods \
        -d src/commons/target \
        ${COMMONS_PATH}/objects/**.java \
        ${COMMONS_PATH}/helpers/**.java \
        src/commons/module-info.java \

    cp -r src/res/** src/commons/target/

    jar -cvf src/mods/${COMMONS_MODULE}.jar -C src/commons/target .
}

# licaza.tdefender.core
build_core() {
    javac \
        --module-path src/mods -d src/core/target \
        ${CORE_PATH}/enemies/**.java \
        ${CORE_PATH}/scenes/**.java \
        ${CORE_PATH}/ui/**.java \
        ${CORE_PATH}/managers/**.java \
        ${CORE_PATH}/inputs/**.java \
        ${CORE_PATH}/main/**.java \
        src/core/module-info.java \

    jar \
        -cvfe src/mods/${CORE_MODULE}.jar \
        ${CORE_MODULE}.main.Game \
        -C src/core/target .
}
    

# # Method for properly list the involved java modules to be build
build_all_java() {
    build_commons
    build_core
}