#!/bin/bash

# # For design compliance, the full name of the module to be build
# # needs to be defined on a variable

COMMONS_PATH=src/commons/licaza/tdefender/engine/commons
TOOLS_PATH=src/tools/licaza/tdefender/engine/tools

COMMONS_MODULE=licaza.tdefender.engine.commons
TOOLS_MODULE=licaza.tdefender.engine.tools


######################################################
# All Java-related commands (Compiling and packaging)
######################################################

# # For design compliance, we define a method for each module

# licaza.tdefender.engine.commons
build_engine_commons() {
    javac \
        --module-path src/mods \
        -d src/commons/target \
        ${COMMONS_PATH}/objects/**.java \
        src/commons/module-info.java 

    jar -cvf src/mods/${COMMONS_MODULE}.jar -C src/commons/target .
}

# licaza.tdefender.engine.tools
build_engine_tools() {
    javac \
        --module-path src/mods \
        -d src/tools/target \
        ${TOOLS_PATH}/math/**.java \
        ${TOOLS_PATH}/helpers/**.java \
        src/tools/module-info.java 

    jar -cvf src/mods/${TOOLS_MODULE}.jar -C src/tools/target .
}
    

# # Method for properly list the involved java modules to be build

# TODO: Rename to build_all_java_modules
build_all_java() {
    build_engine_commons
    build_engine_tools
}