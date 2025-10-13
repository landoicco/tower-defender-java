#!/bin/bash

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
    esac   
}
