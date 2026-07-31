path = "/usr/local/lib/python3.9/site-packages/pyspark/cloudpickle.py"

with open(path, "r") as f:
    lines = f.readlines()

start = None
end = None
for i, line in enumerate(lines):
    if line.startswith("def _make_cell_set_template_code"):
        start = i
    if start is not None and line.strip() == ")(value)":
        end = i
        break

if start is None or end is None:
    print("No se encontro el bloque esperado. start=", start, "end=", end)
else:
    patch_lines = [
        "import ctypes\n",
        "\n",
        "def cell_set(cell, value):\n",
        '    """Set the value of a closure cell using ctypes (compatible con Python 3.8+)."""\n',
        "    ctypes.pythonapi.PyCell_Set(ctypes.py_object(cell), ctypes.py_object(value))\n",
    ]
    new_lines = lines[:start] + patch_lines + lines[end+1:]
    with open(path, "w") as f:
        f.writelines(new_lines)
    print("Parche aplicado: reemplazadas lineas", start+1, "a", end+1)
