#!/bin/bash

source utils.sh
source commands.sh

# Exit from "bash" directory
cd ..

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

# Logic for choosing the desired command
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
    *)
        echo "ERROR: Unknown selection."
        ;;
esac