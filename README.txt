FixBE, the *Fix*ed-structure *B*inary *E*ditor, is a program for editing binary
files in various formats. The development focus thus far has been on supporting
save games from video games.

FixBE uses XML templates that define supported format structures. When opening
a binary file, FixBE tries to automatically detect the appropriate template.
Adding support for another format is simply a matter of creating another
template.
