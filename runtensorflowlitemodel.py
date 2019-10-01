import numpy as np
import tensorflow as tf
import cv2

# Your image path. Here 2000f.jpg is in the same directory
im = cv2.imread("5001.jpg")
im=cv2.resize(im, (1024,1024))
cv2.imshow('img',im)

im=np.expand_dims(im,axis=0)
# Load TFLite model and allocate tensors.
#Path for the TFLTE Model
interpreter = tf.contrib.lite.Interpreter(model_path="inception_float.tflite")
interpreter.allocate_tensors()

# Get input and output tensors.
input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

# Test model on random input data.
input_shape = input_details[0]['shape']
print(input_shape)
# change the following line to feed into your own data.
input_data = np.array(im, dtype=np.float32)
#print(input_data)
interpreter.set_tensor(input_details[0]['index'], input_data)

interpreter.invoke()
output_data = interpreter.get_tensor(output_details[0]['index'])
print(output_data)
