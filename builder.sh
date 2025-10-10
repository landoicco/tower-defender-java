#!/bin/bash

clean() {

    echo "=== Cleaning Java ==="

    # Clean prev builds
    rm -rf src/mods

    # Create module path directory
    mkdir src/mods
}

# Compiles and packages all Java code into modules
build_and_pack() {

    echo "=== Building Java ==="

    # Create commons module
    javac \
        --module-path src/mods -d src/commons/target \
        src/commons/licaza/tdefender/commons/objects/**.java \
        src/commons/licaza/tdefender/commons/helpers/**.java \
        src/commons/module-info.java \

    jar -cvf src/mods/licaza.tdefender.commons.jar -C src/commons/target .

    # Create core module

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

# Start game
play() {
    echo "=== Running Java ==="

    java \
        --module-path src/mods \
        --module licaza.tdefender.core/licaza.tdefender.core.main.Game  
}

echo -e \
"
================================
======== Builder Script ========
================================
"
echo "Available options: clean, build, play, bplay (build & play), install"
read -p "Enter your selection: " action

case "$action" in
    "clean")
        clean
        ;;
    "build")
        clean
        build_and_pack
        ;;
    "play")
        play
        ;;
    "bplay")
        clean
        build_and_pack
        play
        ;;
    "install")
        echo "Installing..."
        ;;
    *)
        echo "ERROR: Unknown selection."
        ;;
esac
