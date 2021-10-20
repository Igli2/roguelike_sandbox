'''
Author: https://www.github.com/Igli2

Input: world/image.png
Execute this script with: python3 texture_splitter.py image
The script will save the 32x32 pixels sub-textures in the folder world/generated/
'''

from PIL import Image
import sys
import math


filename = sys.argv[1]
print(filename)

img = Image.open("world/" + filename + ".png")

width, height = img.size

counter = 0
for x in range(math.ceil(width / 32)):
	for y in range(math.ceil(height // 32)):
		sub_img = img.crop((x * 32, y * 32, x * 32 + 32, y * 32 + 32))
		sub_img.save("world/generated/{}_{}.png".format(filename, counter))
		counter += 1
		sub_img.close()

img.close()
