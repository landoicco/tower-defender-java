#!/bin/bash

# # For design compliance, the full name of the module to be build
# # needs to be defined on a variable

DEMO_PATH=src/demo/licaza/tdefender/demo
AWT_PATH=src/awt/licaza/tdefender/engine/awt
COMMONS_PATH=src/commons/licaza/tdefender/engine/commons
TOOLS_PATH=src/tools/licaza/tdefender/engine/tools
CORE_PATH=src/core/licaza/tdefender/engine/core

DEMO_MODULE=licaza.tdefender.demo
AWT_MODULE=licaza.tdefender.engine.awt
COMMONS_MODULE=licaza.tdefender.engine.commons
TOOLS_MODULE=licaza.tdefender.engine.tools
CORE_MODULE=licaza.tdefender.engine.core


######################################################
# All Java-related commands (Compiling and packaging)
######################################################

# # For design compliance, we define a method for each module

# licaza.tdefender.demo
build_demo() {

    # Safety clean...
    rm -rf src/demo/target
    rm src/mods/${DEMO_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/demo/target \
        ${DEMO_PATH}/configs/**.java \
        ${DEMO_PATH}/actors/enemies/**.java \
        ${DEMO_PATH}/main/**.java \
        ${DEMO_PATH}/scenes/**.java \
        ${DEMO_PATH}/ui/**.java \
        ${DEMO_PATH}/inputs/**.java \
        ${DEMO_PATH}/managers/**.java \
        src/demo/module-info.java 

    cp -r src/res/** src/demo/target/

    jar \
        -cvfe src/mods/${DEMO_MODULE}.jar \
        ${DEMO_MODULE}.main.Game \
        -C src/demo/target .
}

# licaza.tdefender.engine.awt
build_engine_awt() {

    # Safety clean...
    rm -rf src/awt/target
    rm src/mods/${AWT_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/awt/target \
        ${AWT_PATH}/ui/**.java \
        src/awt/module-info.java 

    jar -cvf src/mods/${AWT_MODULE}.jar -C src/awt/target .
}

# licaza.tdefender.engine.commons
build_engine_commons() {

    # Safety clean...
    rm -rf src/commons/target
    rm src/mods/${COMMONS_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/commons/target \
        ${COMMONS_PATH}/misc/**.java \
        ${COMMONS_PATH}/events/**.java \
        ${COMMONS_PATH}/actors/**.java \
        ${COMMONS_PATH}/api/**.java \
        ${COMMONS_PATH}/objects/**.java \
        src/commons/module-info.java 

    jar -cvf src/mods/${COMMONS_MODULE}.jar -C src/commons/target .
}

# licaza.tdefender.engine.tools
build_engine_tools() {

    # Safety clean...
    rm -rf src/tools/target
    rm src/mods/${TOOLS_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/tools/target \
        ${TOOLS_PATH}/math/**.java \
        ${TOOLS_PATH}/helpers/**.java \
        ${TOOLS_PATH}/gui/**.java \
        src/tools/module-info.java 

    jar -cvf src/mods/${TOOLS_MODULE}.jar -C src/tools/target .
}

build_engine_core() {

    # Safety clean...
    rm -rf src/core/target
    rm src/mods/${CORE_MODULE}.jar

    javac \
        --module-path src/mods \
        -d src/core/target \
        ${CORE_PATH}/managers/**.java \
        ${CORE_PATH}/main/**.java \
        src/core/module-info.java

    jar -cvf src/mods/${CORE_MODULE}.jar -C src/core/target .
}
    

# # Method for properly list the involved java modules to be build

build_full_engine() {
    # build_engine_awt
    build_engine_commons
    build_engine_tools
    build_engine_core
}

build_all_java_modules() {
    # # Engine modules...
    build_full_engine

    # # Demo game module...
    build_demo
}
