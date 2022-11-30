import uuid

from locust import HttpUser, task, SequentialTaskSet

from common import token_append, token_random_get, logger
from common.models import Identity
from common.session_worker import SessionWorker


class SessionTaskSet(SequentialTaskSet):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.identity = Identity(str(uuid.uuid4()))
        logger.debug(f"Created identity: {self.identity}")
        self.worker = SessionWorker(self.client)

    @task
    def test_create(self):
        token = self.worker.create(self.identity)
        token_append(token)

    @task(3)
    def test_get(self):
        token = token_random_get()
        self.worker.get(token)


class SessionUser(HttpUser):
    tasks = {SessionTaskSet: 1}
