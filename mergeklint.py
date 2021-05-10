import os, os.path, sys
import glob
from xml.etree import ElementTree

BASE_DIRECTORY = "./"

xml_files = [
    f"{BASE_DIRECTORY}/app/build/reports/ktlint/ktlint-checkstyle.xml",
    f"{BASE_DIRECTORY}/component/build/reports/ktlint/ktlint-checkstyle.xml",
    f"{BASE_DIRECTORY}/data/build/reports/ktlint/ktlint-checkstyle.xml",
    f"{BASE_DIRECTORY}/domain/build/reports/ktlint/ktlint-checkstyle.xml",
    f"{BASE_DIRECTORY}/extensions/build/reports/ktlint/ktlint-checkstyle.xml",

]

# print(os.path.dirname(os.path.abspath(__file__)))

xml_element_tree = None

for xml_file in xml_files:
    print(xml_file)
    if not os.path.exists(xml_file):
        print("asjhd")
        continue
    if os.stat(xml_file).st_size == 0:
        continue

    data = ElementTree.parse(xml_file).getroot()

    for result in data.iter('file'):
        if xml_element_tree is None:
            xml_element_tree = data
            insertion_point = xml_element_tree.findall("./file")
        else:
            insertion_point.extend(result)

if xml_element_tree is not None:
    print(ElementTree.tostring(xml_element_tree))
    f = open( "/Users/wesleysilva/project/poc/CaseStudy/test.xml", 'w' )
    f.write( "\n" )
    f.write( ElementTree.tostring(xml_element_tree).decode("utf-8") )
    f.close()
