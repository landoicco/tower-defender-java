#!/bin/bash

source selections/log.sh
source selections/general.sh
source selections/build.sh

source utils/menus.sh
source utils/outputs.sh

############################
# Bash utility entry point
############################


# # Exit from "bash" directory
cd ..


# # Check if an 'action' was given as argument when executing init.sh

# If no parameters were given...
if [ $# -eq 0 ]; then
    # "Script cosmetics"
    show_headline
    show_quick_menu

    # Capture input from user
    echo_text_bright_pink "Suggestions: build, log"
    read -p "Enter your selection: " param1

    echo_text_bright_pink "Suggestions: engine, demo"
    read -p "Enter command: " param2

# If parameters are given
else
    # For clarity, consider './init.sh <param1> <param2> ...'
    param1=$1 # selection
    param2=$2 # command
fi   


# If selection given is 'log'
if [[ "$param1" == "log" ]]; then
    set_debug_selection $param2

# If selection is 'build'
elif [[ "$param1" == "build" ]]; then
    set_build_selection $param2

else
    set_general_selection $param1
fi   
