package br.com.caelum.editorswitcher;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class SwitcherHandler extends AbstractHandler {
	private static final String PARAMETER = "br.com.caelum.editorswticher.index"; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		int index = Integer.parseInt(event.getParameter(PARAMETER));

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage activePage = window.getActivePage();

		IEditorReference[] references = activePage.getEditorReferences();
		int editorReference;
		if (index == 0)
			editorReference = references.length - 1;
		else
			editorReference = Math.min(index - 1, references.length - 1);

		if (editorReference >= 0) {
			
			IEditorReference reference = references[editorReference];
			IEditorPart editor = reference.getEditor(true);
			activePage.activate(editor);
		}

		return null;
	}
}
