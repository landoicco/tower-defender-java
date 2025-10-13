#!/bin/bash

##############################################################
# Utility methods for formating colorful outputs to the user
##############################################################

# Make text bold
echo_text_bold() {
echo -en "\e[1m$1\e[0m"   
}
# Make text yellow
echo_text_yellow() {
    echo -e "\e[33m$1\e[0m"
}

echo_text_green() {
    echo -e "\e[32m$1\e[0m"
}

echo_text_orange() {
    echo -e "\033[38;5;214m$1\033[0m"   
}

# Make text bold and green
echo_text_bgreen() {
    echo -e "\e[1;32m$1\e[0m"
}

# Make text bold and yellow
echo_text_byellow() {
    echo -e "\e[1;33m$1\e[0m"
}

# Make text bold and red
echo_text_bred() {
    echo -e "\033[1;31m$1\033[0m"
}

echo_text_borange() {
    echo -e "\033[1;38;5;214m$1\033[0m"   
}