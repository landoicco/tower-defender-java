#!/bin/bash

source selections/debug.sh
source selections/general.sh

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
    read -p "Enter your selection: " action

# If parameters are given
else
    action=$1
fi   


# # Check if the user wants 'general' or 'debug/log' tools

# If the commands starts with 'log'
if [[ "$action" == "log"* ]]; then
    echo_text_borange ":::LOG TOOLS:::"
    set_debug_selection $action
else
    echo_text_bgreen ":::GENERAL TOOLS:::"
    set_general_selection $action
fi   
