package com.haustinsl.haustin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AlquilarActivity extends AppCompatActivity {

    private Button btnPasar, btnMeInteresa;
    private FrameLayout cardContainer;
    private TextView likeOverlay, passOverlay;

    private PropertyItem[] propertyItems;
    private int currentPropertyIndex = 0;
    private View currentCardView;

    private float initialX, initialY;
    private boolean isDragging = false;
    private boolean isAnimating = false; // Nueva variable para controlar animaciones

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ELIMINAR LA ACTIONBAR
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_alquilar);

        // Inicializar datos de propiedades
        initializePropertyData();

        // Enlazar elementos
        btnPasar = findViewById(R.id.btnPasar);
        btnMeInteresa = findViewById(R.id.btnMeInteresa);
        cardContainer = findViewById(R.id.cardContainer);
        likeOverlay = findViewById(R.id.likeOverlay);
        passOverlay = findViewById(R.id.passOverlay);

        // Cargar la primera propiedad
        loadCurrentProperty();

        // Configurar listeners de los botones CON ANIMACIÓN
        btnPasar.setOnClickListener(v -> {
            if (!isAnimating && currentCardView != null) {
                animateCardToLeft(); // Animación para botón "Pasar"
            }
        });

        btnMeInteresa.setOnClickListener(v -> {
            if (!isAnimating && currentCardView != null) {
                animateCardToRight(); // Animación para botón "Me interesa"
            }
        });
    }

    private void initializePropertyData() {
        propertyItems = new PropertyItem[] {
                new PropertyItem("1.200€/mes", "Madrid", "3 hab · 80m²"),
                new PropertyItem("950€/mes", "Barcelona", "2 hab · 65m²"),
                new PropertyItem("1.500€/mes", "Valencia", "4 hab · 95m²"),
                new PropertyItem("1.100€/mes", "Sevilla", "3 hab · 75m²")
        };
    }

    private void loadCurrentProperty() {
        if (currentPropertyIndex >= propertyItems.length) {
            showNoMoreProperties();
            return;
        }

        // Inflar la vista de la tarjeta
        currentCardView = LayoutInflater.from(this).inflate(R.layout.item_property_card, cardContainer, false);
        cardContainer.removeAllViews();
        cardContainer.addView(currentCardView);

        // Configurar los datos de la propiedad actual
        PropertyItem currentProperty = propertyItems[currentPropertyIndex];
        TextView priceTextView = currentCardView.findViewById(R.id.priceTextView);
        TextView cityTextView = currentCardView.findViewById(R.id.cityTextView);
        TextView roomsTextView = currentCardView.findViewById(R.id.roomsTextView);

        priceTextView.setText(currentProperty.getPrice());
        cityTextView.setText(currentProperty.getCity());
        roomsTextView.setText(currentProperty.getRooms());

        // Configurar gestos de arrastre en TODA la tarjeta
        setupDragGestures();

        // Resetear overlays y estado de animación
        resetOverlays();
        isAnimating = false;
    }

    private void setupDragGestures() {
        // Configurar el listener en toda la tarjeta
        currentCardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Si hay una animación en curso, ignorar los gestos
                if (isAnimating) {
                    return true;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getRawX();
                        initialY = event.getRawY();
                        isDragging = false;
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - initialX;
                        float deltaY = event.getRawY() - initialY;

                        // Solo considerar arrastre si el movimiento es principalmente horizontal
                        if (Math.abs(deltaX) > Math.abs(deltaY) && Math.abs(deltaX) > 10) {
                            isDragging = true;

                            // Mover la tarjeta
                            v.setTranslationX(deltaX);

                            // Rotación basada en el desplazamiento
                            float rotation = (deltaX / v.getWidth()) * 25f;
                            v.setRotation(rotation);

                            // Mostrar overlay según la dirección
                            if (deltaX > 0) {
                                // Arrastre derecha - Me interesa
                                likeOverlay.setVisibility(View.VISIBLE);
                                passOverlay.setVisibility(View.INVISIBLE);
                                v.setAlpha(1 - Math.min(deltaX / 800f, 0.3f));
                            } else {
                                // Arrastre izquierda - Pasar
                                passOverlay.setVisibility(View.VISIBLE);
                                likeOverlay.setVisibility(View.INVISIBLE);
                                v.setAlpha(1 - Math.min(Math.abs(deltaX) / 800f, 0.3f));
                            }
                            return true;
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if (isDragging && !isAnimating) {
                            float finalDeltaX = event.getRawX() - initialX;

                            // Umbral para considerar el arrastre como acción
                            if (Math.abs(finalDeltaX) > 150) {
                                if (finalDeltaX > 0) {
                                    // Arrastre derecha - Me interesa
                                    animateCardExit(true);
                                } else {
                                    // Arrastre izquierda - Pasar
                                    animateCardExit(false);
                                }
                            } else {
                                // Volver a la posición original si no se superó el umbral
                                resetCardPosition();
                            }
                            isDragging = false;
                            return true;
                        }
                        resetOverlays();
                        break;
                }
                return false;
            }
        });
    }

    // NUEVO MÉTODO: Animación para botón "Pasar"
    private void animateCardToLeft() {
        isAnimating = true;
        passOverlay.setVisibility(View.VISIBLE);
        likeOverlay.setVisibility(View.INVISIBLE);

        currentCardView.animate()
                .translationX(-cardContainer.getWidth())
                .rotation(-25f)
                .alpha(0f)
                .setDuration(400)
                .withEndAction(() -> {
                    handlePassAction();
                    isAnimating = false;
                })
                .start();
    }

    // NUEVO MÉTODO: Animación para botón "Me interesa"
    private void animateCardToRight() {
        isAnimating = true;
        likeOverlay.setVisibility(View.VISIBLE);
        passOverlay.setVisibility(View.INVISIBLE);

        currentCardView.animate()
                .translationX(cardContainer.getWidth())
                .rotation(25f)
                .alpha(0f)
                .setDuration(400)
                .withEndAction(() -> {
                    handleLikeAction();
                    isAnimating = false;
                })
                .start();
    }

    // MÉTODO ORIGINAL (para gestos)
    private void animateCardExit(boolean isLike) {
        isAnimating = true;
        float targetX = isLike ? cardContainer.getWidth() : -cardContainer.getWidth();
        float targetRotation = isLike ? 25f : -25f;

        currentCardView.animate()
                .translationX(targetX)
                .rotation(targetRotation)
                .alpha(0f)
                .setDuration(300)
                .withEndAction(() -> {
                    if (isLike) {
                        handleLikeAction();
                    } else {
                        handlePassAction();
                    }
                    isAnimating = false;
                })
                .start();
    }

    private void resetCardPosition() {
        currentCardView.animate()
                .translationX(0)
                .rotation(0)
                .alpha(1f)
                .setDuration(250)
                .start();

        resetOverlays();
    }

    private void resetOverlays() {
        likeOverlay.setVisibility(View.INVISIBLE);
        passOverlay.setVisibility(View.INVISIBLE);
    }

    private void handlePassAction() {
        Toast.makeText(this, "Pasas de la propiedad " + (currentPropertyIndex + 1), Toast.LENGTH_SHORT).show();
        currentPropertyIndex++;
        loadCurrentProperty();
    }

    private void handleLikeAction() {
        Toast.makeText(this, "¡Te interesa la propiedad " + (currentPropertyIndex + 1) + "!", Toast.LENGTH_SHORT).show();
        currentPropertyIndex++;
        loadCurrentProperty();
    }

    private void showNoMoreProperties() {
        Toast.makeText(this, "No hay más propiedades disponibles", Toast.LENGTH_LONG).show();
        // Opcional: reiniciar el índice para volver al inicio
        // currentPropertyIndex = 0;
        // loadCurrentProperty();
    }
}