#!/bin/bash

##################################################
# Utility methods for formating outputs strings
##################################################

# Make text bold
text_bold() {
echo -en "\e[1m$1\e[0m"   
}
# Make text yellow
text_yellow() {
    echo -e "\e[33m$1\e[0m"
}

# Make text bold and green
text_bgreen() {
    echo -e "\e[1;32m$1\e[0m"
}

# Make text bold and yellow
text_byellow() {
    echo -e "\e[1;33m$1\e[0m"
}

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
}

# Compiles and packages all Java code into modules
build_and_pack() {

    text_byellow "=== Building Java ==="

    # Create commons module
    javac \
        --module-path src/mods \
        -d src/commons/target \
        src/commons/licaza/tdefender/commons/objects/**.java \
        src/commons/licaza/tdefender/commons/helpers/**.java \
        src/commons/module-info.java \

    cp -r src/res/** src/commons/target/

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
    text_byellow "=== Running Java ==="

    java \
        --module-path src/mods \
        --module licaza.tdefender.core/licaza.tdefender.core.main.Game  
}

##################################################
# Logic that show options when running script
##################################################

text_bgreen \
"
================================
======== Builder Script ========
================================
"

text_bold "Quick options: "
text_yellow "clean, build, play, bplay, install"
echo -n "For more options, type "
text_yellow "help"
echo

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
    "help")
        text_byellow "All commands"
        ;;
    *)
        echo "ERROR: Unknown selection."
        ;;
esac
