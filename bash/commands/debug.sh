#!/bin/bash

source commands/general.sh

#########################
# Methods for debugging
#########################

DATE_AND_TIME=$(date '+%F_%H:%M')

generate_log_file() {
    # Create directory if not present
    mkdir logs

    echo_text_orange "Generating log file..."

    # Take argument and assign to variable for clarity
    FILE_PREFIX=$1
    
    exec > ${FILE_PREFIX}_${DATE_AND_TIME}.log
}

# # "Loggers"

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
