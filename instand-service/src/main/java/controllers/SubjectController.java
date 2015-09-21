package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.instand.app.InstandApplicationService;
import com.instand.app.CreateSubjectInput;
import com.instand.domain.Subject;
import lombok.NonNull;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;

import java.util.Optional;

/**
 * Controller of subjects.
 */
@Singleton
public class SubjectController {

    private InstandApplicationService service;

    @Inject
    public SubjectController(@NonNull InstandApplicationService service) {
        this.service = service;
    }

    public Result create(@NonNull CreateSubjectInput input) {
        Subject subject = service.createSubject(input);
        return Results.json().render(subject);
    }

    public Result get(@PathParam("id") String id) {
        Optional<Subject> optSubject = service.getSubject(id);
        if (!optSubject.isPresent()) {
            return Results.json()
                    .status(Result.SC_404_NOT_FOUND)
                    .render(Error.notFound());
        }
        return Results.json().render(service.getSubject(id));
    }

}
