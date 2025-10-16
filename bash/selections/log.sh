#!/bin/bash

source commands/log.sh

set_debug_selection() {
    echo_text_borange "::: LOG TOOLS :::"

    case $1 in
    "build")
        clean
        build_while_log
        ;;
    "bplay")
        clean
        build_and_play_while_log
        ;;
    "play")
        play_while_log
        ;;
    # Clear 'logs' directory
    "clear")
        rm -rf logs/
        echo_text_orange "Logs cleared!"
        ;;
    *)
        echo_text_bred "ERROR: Unknown log command."
        ;;
    esac  
}
