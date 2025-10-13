#!/bin/bash

source commands/general.sh

set_general_selection() {
    case $1 in
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
        text_bred "ERROR: Unknown selection."
        ;;
    esac   
}
