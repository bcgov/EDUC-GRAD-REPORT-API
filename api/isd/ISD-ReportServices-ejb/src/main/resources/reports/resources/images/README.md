Purpose
==========
This directory contains images used on various reports and report formats.

Usage
==========
File usage is described in the following subsections.

Transcript (HTML/PDF)
----------
* `logo_bc_bw.svg` - Official version
* `logo_yu_bw.svg` - Official version
* `logo_bc.svg`
* `logo_yu.svg`

PEAR, GNA (HTML/PDF)
----------
* `logo_bc_moe.svg`
* `logo_yu_moe.svg`

Unused
----------
* `logo_bc_moe_bw.svg`

Conversion
==========
Transforming the files from EPS to SVG was accomplished as follows:

1. for i in $(find . -type f -name "\*.eps"); do epstopdf "$i"; done
1. for i in $(find . -type f -name "\*.pdf"); do pdf2svg $i $(basename $i .pdf).svg; done

Transforming into PNG was accomplished as follows:

1. for i in \*svg; do rsvg-convert $i -a -d 300 -p 300 -f png -o $(basename $i .svg).png; done

The PNG files are not used by the application, as the original SVG files
are converted on-the-fly by the reporting engine.

