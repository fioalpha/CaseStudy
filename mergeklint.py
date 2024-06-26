#!/usr/bin/env bash

import os, os.path, sys
import glob
from xml.etree import ElementTree

BASE_DIRECTORY = "/bitrise/src"

xml_files = [
    "app/build/reports/ktlint/ktlint-checkstyle.xml",
    "app/build/reports/detekt/detekt-checkstyle.xml",
    "component/build/reports/ktlint/ktlint-checkstyle.xml",
    "component/build/reports/detekt/detekt-checkstyle.xml",
    "data/build/reports/ktlint/ktlint-checkstyle.xml",
    "data/build/reports/detekt/ktlint-checkstyle.xml",
    "domain/build/reports/ktlint/ktlint-checkstyle.xml",
    "domain/build/reports/detekt/detekt-checkstyle.xml",
    "extensions/build/reports/ktlint/ktlint-checkstyle.xml",
    "extensions/build/reports/detekt/detekt-checkstyle.xml",

]

xml_element_tree = None

print("lkzsdlkasdkl")

for xml_file in xml_files:
    print("12123")
    if not os.path.exists(xml_file):
        continue;
    if os.stat(xml_file).st_size == 0:
        continue;

    print("12   qwq123")
    data = ElementTree.parse(xml_file).getroot()

    for result in data.iter('file'):
        if xml_element_tree is None:
            xml_element_tree = data
            insertion_point = xml_element_tree.findall("./file")
        else:
            insertion_point.extend(result)

if xml_element_tree is not None:
    # print(ElementTree.tostring(xml_element_tree))
    f = open( "test.xml", 'w' )
    f.write( "\n" )
    f.write( ElementTree.tostring(xml_element_tree).decode("utf-8") )
    f.close()
    if os.path.exists(xml_file):
        print("FILE_NOT_EXIST")
