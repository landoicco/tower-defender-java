#!/bin/bash

source utils.sh

##################################################
# Methods for each listed option in the script
##################################################

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

    # deps
    echo -ne "\tList modules and show dependency graphs - "
    text_yellow "deps"

    # clean
    echo -ne "\tClean all previous builds - "
    text_yellow "clean"

    # build
    echo -ne "\tCompile code and pack modules - "
    text_yellow "build"

    # bplay
    echo -ne "\tBuild source code and play game - "
    text_yellow "bplay"

    # install
    echo -ne "\tCreate executable of this game - "
    text_yellow "install"

    # help
    echo -ne "\tList all available commands - "
    text_yellow "help"

    # play
    echo -ne "\tPlay game - "
    text_yellow "play"

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