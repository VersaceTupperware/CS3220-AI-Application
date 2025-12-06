@import java.util.*
@import dev.cs3220project1.cs3220aiapplication.models.*
@import dev.cs3220project1.cs3220aiapplication.util.JsonHelper

@param Meal meal

<div class="recipe-card mb-3">
    <div class="d-flex align-items-center justify-content-between">
        <h5 class="fw-semibold mb-1">
            ${meal.name()}
        </h5>
        @if(meal.type() != null)
            <span class="badge badge-chip">${meal.type()}</span>
        @endif
    </div>

    <div class="small recipe-meta mb-2">
        @if(meal.calories() != null)
            ~ ${meal.calories()} kcal per serving
        @endif
    </div>

    <div class="mb-2">
        <strong>Ingredients</strong>
        <ul class="mb-0">
            @if(meal.ingredients() != null)
                @for(Ingredient it : meal.ingredients())
                    <li>
                        @if(it != null)
                            ${it.toString()}
                        @else
                            !{
                            var item = it.item();
                            var qty  = it.quantity();
                            var unit = it.unit();
                            var notes= it.notes();
                            }
                            ${item} ${qty}${unit != null ? " " + unit : ""}${notes != null && !notes.isBlank() ? " (" + notes + ")" : ""}
                        @endif
                    </li>
                @endfor
            @else
                !{
                var raw = Objects.toString(meal.ingredients(), "");
                var parts = raw.split("\\s*,\\s*");
                }
                @for(String p : parts)
                    @if(!p.isBlank())
                        <li>${p}</li>
                    @endif
                @endfor
            @endif
        </ul>
    </div>

    <div class="mt-3">
        <strong>Instructions</strong>
        <ol class="mb-2">
            @if(meal.instructions() != null)
                @for(Step st : meal.instructions())
                    <li>
                        @if(st.text() != null)
                            ${st.text()}
                        @endif
                    </li>
                @endfor
            @else
                !{
                var raw = Objects.toString(meal.instructions(), "");
                var numbered = raw.split("\\s*\\d+\\.\\s*");
                }
                @if(numbered.length > 1)
                    @for(String s : numbered)
                        @if(!s.isBlank())
                            <li>${s}</li>
                        @endif
                    @endfor
                @else
                    !{
                    var parts2 = raw.split("\\s*,\\s*");
                    }
                    @for(String s : parts2)
                        @if(!s.isBlank())
                            <li>${s}</li>
                        @endif
                    @endfor
                @endif
            @endif
        </ol>
    </div>

    @if(meal.nutrition() != null)
        !{var n = meal.nutrition();}

        <div class="mt-2">
            <strong>Nutrition (per serving)</strong>
            <div class="small">
                @if(n.proteinGrams() != null) Protein ${n.proteinGrams()}g · @endif
                @if(n.carbsGrams()   != null) Carbs ${n.carbsGrams()}g ·   @endif
                @if(n.fatGrams()     != null) Fat ${n.fatGrams()}g ·       @endif
                @if(n.fiberGrams()   != null) Fiber ${n.fiberGrams()}g ·   @endif
                @if(n.sodiumMg()     != null) Sodium ${n.sodiumMg()}mg     @endif
            </div>
        </div>
    @endif

    <details class="mt-2">
        <summary class="small">Raw JSON</summary>
        <pre class="mono small mb-0">${JsonHelper.toJson(meal)}</pre>
    </details>
</div>
