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

##################
# Debugging
##################

DATE_AND_TIME=$(date '+%F_%H:%M')

generate_log_file() {
    # Create directory if not present
    mkdir logs

    text_orange "Generating log file..."

    # Take argument and assign to variable for clarity
    FILE_PREFIX=$1
    
    exec > ${FILE_PREFIX}_${DATE_AND_TIME}.log
}

# # Same commands but generating a log file

# Build and pack
build_while_log() {
    generate_log_file "logs/build"

    echo \
    "
    ################################
    # Build logs (${DATE_AND_TIME})
    ################################
    "

    clean
    build_and_pack
}

# Build, pack and play
build_and_play_while_log() {

    generate_log_file "logs/build_and_play"

    echo \
    "
    ########################################
    # Build & Play logs (${DATE_AND_TIME})
    ########################################
    "

    clean
    build_and_pack
    play
}

play_while_log() {
    generate_log_file "logs/play"

    echo \
    "
    ###############################
    # Play logs (${DATE_AND_TIME})
    ###############################
    "

    play
}
