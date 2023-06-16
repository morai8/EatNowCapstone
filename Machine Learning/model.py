import numpy as np
import pandas as pd
import tensorflow as tf
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import Adam
from keras import regularizers

# Read Dataset
recipes_data = pd.read_csv('./Dataset/Recipes.csv')

# Pre-processing Data
all_ingredients = recipes_data['ingredients'].str.split(',').tolist()
recipes = recipes_data['title'].tolist()

unique_ingredients = list(set([ingredient for sublist in all_ingredients for ingredient in sublist]))
ingredient_map = {ingredient: i for i, ingredient in enumerate(unique_ingredients)}

X_train = np.zeros((len(all_ingredients), len(unique_ingredients)))
for i, recipe_ingredients in enumerate(all_ingredients):
    for ingredient in recipe_ingredients:
      ingredient_index = ingredient_map[ingredient]
      X_train[i, ingredient_index] = 1

# Get Input from User
user_input = []
for _ in range(4):
    ingredient = input("Enter an ingredient: ")
    user_input.append(ingredient)

# Preprocess user input
X_user = np.zeros((1, len(unique_ingredients)))
for ingredient in user_input:
    if ingredient in ingredient_map:
        ingredient_index = ingredient_map[ingredient]
        X_user[0, ingredient_index] = 1
        
# Model
model = Sequential([
    Dense(256, activation='relu', input_shape=(len(unique_ingredients),), kernel_regularizer=regularizers.l2(0.0001)),
    Dense(128, activation='relu', kernel_regularizer=regularizers.l2(0.0001)),
    Dense(64, activation='relu', kernel_regularizer=regularizers.l2(0.0001)),
    tf.keras.layers.Dropout(0.5),
    Dense(len(recipes), activation='softmax'),
])
model.compile(loss='categorical_crossentropy', optimizer=Adam(learning_rate=0.001), metrics=['accuracy'])
model.fit(X_train, pd.get_dummies(recipes), epochs=50)

# Predictions
predictions = model.predict(X_user)[0]
sorted_indices = np.argsort(predictions)[::-1]
sorted_predictions = predictions[sorted_indices]
# Recommend top 15 food recipes based on matching ingredients
top_15_recipes = [recipes[i] for i in sorted_indices[:15]]
for recipe in top_15_recipes:
    print(recipe)