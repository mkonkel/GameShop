package presentation

import presentation.app.AppPresentation

interface PresentationFactory {
    fun createAppPresentation(): AppPresentation
}
