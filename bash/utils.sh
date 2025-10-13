#!/bin/bash

source java.sh

# Utility method for properly list the involved java modules
build_all_java() {
    # Compile commons module
    build_commons
   
    # Compile core module
    build_core
}


###################################################
# Utility methods for showing a "menu" to the user
###################################################

# Show script headline
show_headline() {
text_bgreen \
"
================================
======== Builder Script ========
================================
"
}

# Show quick menu
show_quick_menu() {
    text_bold "Quick options: "
    text_yellow "clean, build, play, bplay, install"
    echo -n "For more options, type "
    text_yellow "help"
    echo
}

# Show long menu
show_long_menu() {
    # deps
    echo -ne "\tList modules and show dependency graphs - "
    text_yellow "deps"

    # clean
    echo -ne "\tClean all previous builds - "
    text_yellow "clean"

    # build
    echo -ne "\tCompile code and pack modules - "
    text_yellow "build"

    # bplay
    echo -ne "\tBuild source code and play game - "
    text_yellow "bplay"

    # install
    echo -ne "\tCreate executable of this game - "
    text_yellow "install"

    # help
    echo -ne "\tList all available commands - "
    text_yellow "help"

    # play
    echo -ne "\tPlay game - "
    text_yellow "play"
}


##################################################
# Utility methods for formating outputs strings
##################################################

# Make text bold
text_bold() {
echo -en "\e[1m$1\e[0m"   
}
# Make text yellow
text_yellow() {
    echo -e "\e[33m$1\e[0m"
}

text_green() {
    echo -e "\e[32m$1\e[0m"
}

text_orange() {
    echo -e "\033[38;5;214m$1\033[0m"   
}

# Make text bold and green
text_bgreen() {
    echo -e "\e[1;32m$1\e[0m"
}

# Make text bold and yellow
text_byellow() {
    echo -e "\e[1;33m$1\e[0m"
}

# Make text bold and red
text_bred() {
    echo -e "\033[1;31m$1\033[0m"
}