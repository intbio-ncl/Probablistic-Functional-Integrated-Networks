import dash
import dash_core_components as dcc
import dash_html_components as html
from dash.dependencies import Input, Output

import numpy as np
import pandas as pd

data = pd.read_csv("LLS.txt", delimiter="\t")
data = data.rename(columns={"126":"V126"})
for col in data.columns:
    if not col.startswith("V"):
        continue
    data[col] = data[col].astype(float)


external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css']

app = dash.Dash(__name__, external_stylesheets=external_stylesheets)

dataset_options = [{'label': i, 'value': i} for i in data["DatasetsNames"]]
dataset_options.sort(key=lambda i:i['label'])

app.layout = html.Div(children=[
    html.Div([
        dcc.Dropdown(
            id='select_dataset',
            options=dataset_options,
            value=dataset_options[0]["value"]
        ),
    dcc.Graph(
        id='example-graph',
    )
    ]),


])

@app.callback(
    Output('example-graph', 'figure'),
    [Input('select_dataset', 'value'),]
)
def update_graph(select_dataset):
    print(select_dataset)
    row = data[ data["DatasetsNames"] == select_dataset ]
    tmp = dict(row.iloc[0])
    del tmp["DatasetsNames"]
    x = sorted(list(tmp.keys()), key=lambda i:int(i[1:]))
    y = [tmp[i] for i in x]

    return {
        "data": 
            [{"x":x, "y":y}],
        "layout": {
            "title": select_dataset,
            "yaxis": {
                "range": [
                    0,
                    10
                ]
            }
        }
    }

if __name__ == '__main__':
    app.run_server(debug=True)
