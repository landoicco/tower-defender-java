#!/bin/bash

source utils.sh

###############################
# Most frequently used methods
###############################

clean() {

    text_byellow "=== Cleaning Java ==="

    # Destroy all .class files
    rm -rf src/**/target

    # Clean prev builds
    rm -rf src/mods

    # Create module path directory
    mkdir src/mods

    text_green "Cleaned!"
}

# Compiles and packages all Java code into modules
build_and_pack() {

    text_byellow "=== Building Java ==="

    build_all_java

    text_green "Java modules compiled and packaged!"

}

# Start game
play() {

    text_byellow "=== Running Java ==="

    java \
        --module-path src/mods \
        --module licaza.tdefender.core/licaza.tdefender.core.main.Game  
}

# Show to the user all available commands 
help() {
    text_byellow "=== ALL COMMANDS ==="
    show_long_menu
}

deps() {
    # For sanity, make a fresh build
    build_and_pack

    text_byellow "=== Dependency graph ==="

    text_bold "Modules found:\n\t"
    ls src/mods

    echo
    jdeps -s src/mods/*
}