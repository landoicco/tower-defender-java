#!/bin/bash

source commands/debug.sh

set_debug_selection() {
    case $1 in
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
    "logclear")
        rm -rf logs/
        echo_text_orange "Logs cleared!"
        ;;
    *)
        echo_text_bred "ERROR: Unknown selection."
        ;;
    esac  
}
