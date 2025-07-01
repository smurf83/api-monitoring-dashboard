import requests
import pandas as pd
import matplotlib.pyplot as plt
from io import BytesIO

# Step 1: Connect to a Public API (Example: Exchange Rates)
api_url = "https://api.exchangerate.host/timeseries"
params = {
    "start_date": "2024-06-01",
    "end_date": "2024-06-30",
    "base": "EUR",
    "symbols": "USD"
}
response = requests.get(api_url, params=params)
data = response.json()

# Step 2: Convert API Response to DataFrame
if data["success"]:
    rates = data["rates"]
    df = pd.DataFrame(rates).T  # Transpose to have dates as rows
    df.index = pd.to_datetime(df.index)
    df.columns = ["EUR to USD"]
else:
    df = pd.DataFrame()

# Step 3: Basic Analysis
summary_stats = df.describe()

# Step 4: Create a Plot
plt.figure(figsize=(10, 5))
plt.plot(df.index, df["EUR to USD"], marker="o")
plt.title("EUR to USD Exchange Rate (June 2024)")
plt.xlabel("Date")
plt.ylabel("Exchange Rate")
plt.grid(True)
img_bytes = BytesIO()
plt.savefig(img_bytes, format='png')
img_bytes.seek(0)

# Prepare results for display
summary_stats_output = summary_stats.to_string()

summary_stats_output[:1000]  # Display trimmed output to keep it readable
