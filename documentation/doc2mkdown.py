#!/usr/bin/env python
"""Convert a word (doc/docx) file to markdown"""
import sys
import os
import subprocess


SOFFICE = r'/Applications/LibreOffice.app/Contents/MacOS/soffice'
PANDOC = r'pandoc'


def convert(infile, outfile):
    """Convert the given infile to the given outfile in markdown format, via
    LibreOffice and Pandoc"""
    root, __ = os.path.splitext(infile)
    htmlfile = root + ".html"
    subprocess.call([SOFFICE, '--invisible', '--convert-to',
                     'html', infile])
    subprocess.call([PANDOC, '-f', 'html', '-t', 'markdown', '-o', outfile,
                     htmlfile])
    os.remove(htmlfile)


def main(argv=None):
    from optparse import OptionParser
    if argv is None:
        argv = sys.argv
    arg_parser = OptionParser(
    usage = "usage: %prog DOCFILE",
    description = __doc__)
    options, args = arg_parser.parse_args(argv)
    try:
        docfile = args[1]
    except IndexError:
        arg_parser.error("must give DOCFILE")
    root, __ = os.path.splitext(docfile)
    mdfile = root + ".md"
    convert(docfile, mdfile)


if __name__ == "__main__":
    sys.exit(main())
