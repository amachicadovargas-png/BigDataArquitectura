import re

path = "/usr/local/lib/python3.9/site-packages/pyspark/cloudpickle.py"

with open(path, "r") as f:
    content = f.read()

# Reemplaza la función problemática por una versión compatible usando ctypes
patch = '''import ctypes

def _make_cell_set_template_code():
    return None

def cell_set(cell, value):
    """Set the value of a closure cell using ctypes (compatible con Python 3.8+)."""
    ctypes.pythonapi.PyCell_Set(ctypes.py_object(cell), ctypes.py_object(value))
'''

new_content = re.sub(
    r"def _make_cell_set_template_code\(\):.*?return cell_set\n",
    patch,
    content,
    flags=re.S
)

with open(path, "w") as f:
    f.write(new_content)

print("Parche aplicado correctamente.")
