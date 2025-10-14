#!/bin/bash

# # For design compliance, the full name of the module to be build
# # needs to be defined on a variable

DEMO_PATH=src/demo/licaza/tdefender/demo
AWT_PATH=src/awt/licaza/tdefender/engine/awt
COMMONS_PATH=src/commons/licaza/tdefender/engine/commons
TOOLS_PATH=src/tools/licaza/tdefender/engine/tools

DEMO_MODULE=licaza.tdefender.demo
AWT_MODULE=licaza.tdefender.engine.awt
COMMONS_MODULE=licaza.tdefender.engine.commons
TOOLS_MODULE=licaza.tdefender.engine.tools


######################################################
# All Java-related commands (Compiling and packaging)
######################################################

# # For design compliance, we define a method for each module

# licaza.tdefender.demo
build_demo() {
    javac \
        --module-path src/mods \
        -d src/demo/target \
        ${DEMO_PATH}/configs/**.java \
        ${DEMO_PATH}/actors/enemies/**.java \
        ${DEMO_PATH}/actors/towers/**.java \
        src/demo/module-info.java 

    jar -cvf src/mods/${DEMO_MODULE}.jar -C src/demo/target .
}

# licaza.tdefender.engine.awt
build_engine_awt() {
    javac \
        --module-path src/mods \
        -d src/awt/target \
        ${AWT_PATH}/ui/**.java \
        src/awt/module-info.java 

    jar -cvf src/mods/${AWT_MODULE}.jar -C src/awt/target .
}

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
    build_demo
    build_engine_awt
    build_engine_commons
    build_engine_tools
}