#!/bin/bash

source utils.sh
source commands.sh

# Exit from "bash" directory
cd ..

##################################################
# Logic that show options when running script
##################################################

# # Check if an 'action' was given as argument when running init.sh

# If no parameters were given...
if [ $# -eq 0 ]; then
    # Presentation of the script
    show_headline
    show_quick_menu

    read -p "Enter your selection: " action
    
# If parameters are given
else
    action=$1
fi   

# # Logic for choosing the desired command
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
        help
        ;;
    "deps")
        deps
        ;;
    ################
    # Debug options
    ################
    "logbuild")
        clean
        build_while_log
        ;;
    "logbplay")
        clean
        build_and_play_while_log
        ;;
    "logplay")
        play_while_log
        ;;
    # Clear 'logs' directory
    "clogs")
        rm -rf logs/
        text_orange "Logs cleared!"
        ;;
    *)
        text_bred "ERROR: Unknown selection."
        ;;
esac
