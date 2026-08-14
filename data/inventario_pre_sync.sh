#!/bin/bash
# ============================================================================
# INVENTARIO PRE-SINCRONIZACIÓN — no modifica nada, solo lee
# Correr esto ANTES de git fetch / git reset --hard
# ============================================================================

echo "=========================================================="
echo "1. ¿En qué rama estamos y qué commit local tenemos?"
echo "=========================================================="
git status
echo ""
git log -1 --oneline
echo ""
git branch -vv


echo "=========================================================="
echo "2. ¿Hay cambios locales sin commitear? (esto es lo que se"
echo "   PERDERÍA con git reset --hard origin/master)"
echo "=========================================================="
git status --porcelain
echo ""
echo "--- Diff resumido de archivos modificados ---"
git diff --stat


echo "=========================================================="
echo "3. ¿Hay archivos nuevos que git no está trackeando todavía?"
echo "   (estos NO se borran con reset --hard, pero sí con"
echo "   git clean -fd, así que ojo si después corremos eso)"
echo "=========================================================="
git status --porcelain | grep "^??"


echo "=========================================================="
echo "4. Cuánto se desvió el local del remoto (cuántos commits"
echo "   de diferencia en cada sentido)"
echo "=========================================================="
git fetch origin --dry-run 2>&1
git rev-list --left-right --count HEAD...origin/master 2>/dev/null || echo "(correr git fetch primero para tener origin/master actualizado)"


echo "=========================================================="
echo "5. Listado completo de notebooks y scripts relevantes,"
echo "   con fecha de última modificación"
echo "=========================================================="
find . -maxdepth 2 \( -name "*.ipynb" -o -name "*.py" -o -name "*.sh" \) \
  -not -path "./.git/*" \
  -exec ls -la {} \; | sort -k6,7


echo "=========================================================="
echo "6. Contenido puntual de los 3 notebooks del entregable"
echo "   (para confirmar si tienen los ajustes que ya hicimos)"
echo "=========================================================="
for nb in "6 Batch Propuesto.ipynb" "5 Streaming.ipynb" "7 Serving.ipynb"; do
  echo "--- $nb ---"
  if [ -f "$nb" ]; then
    echo "Existe. Tamaño: $(du -h "$nb" | cut -f1). Última modificación: $(stat -c '%y' "$nb" 2>/dev/null || stat -f '%Sm' "$nb")"
    # Marca si tiene código ya ejecutado (execution_count != null) o está "limpio"
    if grep -q '"execution_count": null' "$nb" 2>/dev/null; then
      echo "  -> Contiene celdas SIN ejecutar (execution_count: null)"
    fi
    if grep -q "INTERVAL 20 MINUTES" "$nb" 2>/dev/null; then
      echo "  -> Tiene la ventana de 20 MINUTES (versión ajustada de Serving)"
    fi
  else
    echo "NO existe en esta ruta"
  fi
  echo ""
done


echo "=========================================================="
echo "7. Scripts auxiliares de materialización que armamos"
echo "   (materializar_stream_join_speed*.py)"
echo "=========================================================="
find . -maxdepth 2 -iname "materializar_stream*" -not -path "./.git/*" -exec ls -la {} \;


echo "=========================================================="
echo "8. Zip de entrega, si existe, y qué tiene adentro"
echo "=========================================================="
find . -maxdepth 2 -iname "*entrega*.zip" -not -path "./.git/*" -exec ls -la {} \;
find . -maxdepth 2 -iname "*entrega*.zip" -not -path "./.git/*" -exec unzip -l {} \;


echo "=========================================================="
echo "FIN DEL INVENTARIO — todavía no se tocó nada."
echo "Revisar los puntos 2 y 3 antes de decidir si conviene:"
echo "  a) commitear/guardar lo local primero (git stash, o commit"
echo "     a una rama aparte), o"
echo "  b) descartarlo directo con git reset --hard origin/master"
echo "=========================================================="
