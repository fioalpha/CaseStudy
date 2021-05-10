#!/usr/bin/env bash

import os, os.path, sys
import glob
from xml.etree import ElementTree

BASE_DIRECTORY = "/bitrise/src"

xml_files = [
    "/bitrise/src/app/build/reports/ktlint/ktlint-checkstyle.xml",
    # f"{BASE_DIRECTORY}/component/build/reports/ktlint/ktlint-checkstyle.xml",
    # f"{BASE_DIRECTORY}/data/build/reports/ktlint/ktlint-checkstyle.xml",
    # f"{BASE_DIRECTORY}/domain/build/reports/ktlint/ktlint-checkstyle.xml",
    # f"{BASE_DIRECTORY}/extensions/build/reports/ktlint/ktlint-checkstyle.xml",

]

xml_element_tree = None

for xml_file in xml_files:
    if not os.path.exists(xml_file):
        continue;
    if os.stat(xml_file).st_size == 0:
        continue;

    data = ElementTree.parse(xml_file).getroot()

    for result in data.iter('file'):
        if xml_element_tree is None:
            xml_element_tree = data
            insertion_point = xml_element_tree.findall("./file")
        else:
            insertion_point.extend(result)

if xml_element_tree is not None:
    # print(ElementTree.tostring(xml_element_tree))
    f = open( "/bitrise/src/test.xml", 'w' )
    f.write( "\n" )
    f.write( ElementTree.tostring(xml_element_tree).decode("utf-8") )
    f.close()
