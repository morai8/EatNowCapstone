import csv
import requests
import traceback
from pydantic import BaseModel
from fastapi import FastAPI, Response

app = FastAPI()

class Recipe(BaseModel):
    title: str
    URL: str
    total_time: int
    description: str
    ingredients: list[str]
    instructions: list[str]
    calories: str
    serves: str

def get_recipes_data():
    url = "https://storage.googleapis.com/c23-ps470-eatnow/Recipes_v2.csv"
    response = requests.get(url)
    if response.status_code == 200:
        csv_data = response.text
        recipes_data = []
        reader = csv.DictReader(csv_data.splitlines())
        for row in reader:
            recipe = {
                "title": row["title"],
                "URL": row["URL"],
                "total_time": float(row["total_time"]),  # Mengubah menjadi float
                "description": row["description"],
                "ingredients": row["ingredients"].split("|"),
                "instructions": row["instructions"].split("|"),
                "calories": row["calories"],
                "serves": row["serves"]
            }
            recipes_data.append(recipe)
        return recipes_data
    else:
        raise Exception("Failed to retrieve recipes data.")


@app.get("/")
def index():
    return "Hello world from ML endpoint!"

@app.get("/recipes")
def get_recipes(response: Response):
    try:
        recipes_data = get_recipes_data()
        recipes = [Recipe(**recipe) for recipe in recipes_data]
        return recipes
    except Exception as e:
        traceback.print_exc()
        response.status_code = 500
        return "Internal Server Error"

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host='0.0.0.0', port=8000)
