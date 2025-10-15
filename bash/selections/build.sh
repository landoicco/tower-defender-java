#!/bin/bash

source commands/build.sh


set_build_selection() {
    # Show selected 'selection'
    echo_text_bgreen "::: BUILD TOOLS :::"

    # To prevent errors...
    mkdir src/mods

    case $1 in
    # # Core modules
    # Demo game
    "demo")
        demo
        ;;
    # Full engine
    "engine")
        engine
        ;;
        
    # # Specific modules
    "commons")
        commons
        ;;
    "tools")
        tools
        ;;
    "awt")
        awt
        ;;
    "all")
        all
        ;;
    *)
        echo_text_bred "ERROR: That module does not exist!"
        ;;
    esac  
}