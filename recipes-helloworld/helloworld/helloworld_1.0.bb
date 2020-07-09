#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#
SUMMARY = "Hello World Demo"
DESCRIPTION = "Simple helloworld application"
SECTION = "apps"
APP_NAME = "helloworld"
LICENSE = "CLOSED"

#####################################################
# Detail Variable, please check following link. 
# https://www.yoctoproject.org/docs/3.1/ref-manual/ref-manual.html
#
# You also can fast check from this link.
# https://www.yoctoproject.org/docs/3.1/ref-manual/ref-manual.html#var-{varialbe you want to know}
#
# Ex: if I want check variable "PN", you can goto this link.
# https://www.yoctoproject.org/docs/3.1/ref-manual/ref-manual.html#var-PN
#####################################################

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

# Get hello world example code from git
#SRC_URI = "git://github.com/wenjuin716/helloworld.git;protocol=https"
#SRCREV = "620b55c7e6986fd08a34facd423faad39ec91ca4"

# Get hello world example code from file
SRC_URI = "file://*"

inherit autotools

# The autotools configuration I am basing this on seems to have a problem with a race condition when parallel make is enabled
PARALLEL_MAKE = ""


#####################################################
# Build Task Section
# you can modify this section to customize build task
# More description: https://www.yoctoproject.org/docs/3.1/ref-manual/ref-manual.html
#
# if you want check definition of "do_compile", you can goto this link.
# https://www.yoctoproject.org/docs/3.1/ref-manual/ref-manual.html#ref-tasks-compile
#####################################################

localdir = "/usr/local"
bindir = "${localdir}/bin"

## default action is "oe_runmake" when missing do_compile ##
do_compile() {
    # WorkaRound Solution for SRC_URI = "file://*"
    # Due to default build dir is ${B}, but source code is in ${WORKDIR}.
    # I have no idea for this, so workaround it.
    make -C ${WORKDIR}

    # WorkaRound Solution for SRC_URI = "git://github.com/wenjuin716/helloworld.git;protocol=https"
    # Due to default build dir is ${B}, but source code is in ${WORKDIR}/git.
    # I have no idea for this, so workaround it.
#    make -C ${WORKDIR}/git
}

do_install () {
    install -m 0755 -d ${D}${bindir}
    cd ${WORKDIR}	# WorkaRound Solution for SRC_URI = "file://*"
#    cd ${WORKDIR}/git	# WorkaRound Solution for SRC_URI = "git://github.com/wenjuin716/helloworld.git;protocol=https"
    install -m 0755 ${APP_NAME} ${D}${bindir}
}

