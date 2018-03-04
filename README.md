# Image-Color-Changer
Application for changing numbers of colors in image. 

It divides image colors into X number of groups and counts their centers. Then it changes every color to the closest center.

Algorithm:
1. Find all colors in picture
2. Create X color groups
3. Find X random colors and assign center of that group to this color
4. Add remaining colors to groups with closest center
5. Calculate group centers as average of all colors in that group
5. Remove all colors from groups
6. Repeat for all colors:
   - Add color to group with closest center
   - Calculate new center as average of all colors in group
7. New group centers are colors you were waiting for

Main GUI:

![Alt text](/readmeImages/gui.jpg "Optional Title")

EXAMPLES:
Changing to 16 colors:

![Alt text](/readmeImages/example11.jpg "Optional Title")
![Alt text](/readmeImages/example12.jpg "Optional Title")

Changing to 128 colors:

![Alt text](/readmeImages/example11.jpg "Optional Title")
![Alt text](/readmeImages/example13.jpg "Optional Title")

Changing to 8 colors:

![Alt text](/readmeImages/example21.jpg "Optional Title")
![Alt text](/readmeImages/example22.jpg "Optional Title")
