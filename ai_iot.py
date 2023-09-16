import paho.mqtt.client as mqtt
import numpy as np
import tensorflow as tf

# Define MQTT client
client = mqtt.Client()

# Connect to MQTT broker
client.connect("localhost", 1883)

# Subscribe to topic
client.subscribe("water_flow")

# Create TensorFlow model
model = tf.keras.models.Sequential([
  tf.keras.layers.Dense(128, activation='relu', input_shape=(1,)),
  tf.keras.layers.Dense(64, activation='relu'),
  tf.keras.layers.Dense(1, activation='sigmoid')
])

# Load model weights
model.load_weights('water_flow_prediction_model.h5')

# Start loop to read and process data
while True:

  # Receive message from MQTT broker
  message = client.receive().payload

  # Convert message to float
  water_flow = float(message)

  # Make prediction
  prediction = model.predict([[water_flow]])

  # Check if prediction is above threshold
  if prediction > 0.5:
    # Leak detected!
    print("Leak detected!")
  else:
    # No leak detected
    print("No leak detected")
