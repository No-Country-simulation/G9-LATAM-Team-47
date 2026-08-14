class FinanceAIError(Exception):
    def __init__(self, message, status_code=None, details=None):
        super().__init__(message)
        self.status_code = status_code
        self.details = details


class AuthenticationError(FinanceAIError):
    pass


class AuthorizationError(FinanceAIError):
    pass


class ValidationError(FinanceAIError):
    pass


class ConflictError(FinanceAIError):
    pass


class ResourceNotFoundError(FinanceAIError):
    pass


class BackendUnavailableError(FinanceAIError):
    pass


class BackendError(FinanceAIError):
    pass
