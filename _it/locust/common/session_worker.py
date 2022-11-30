from locust.clients import HttpSession

from common import logger
from common.models import Identity


class SessionWorker(object):
    def __init__(self, client: HttpSession):
        self.client = client

    def create(self, identity: Identity) -> str:
        """
        Create session
        :param account:
        :return: access token
        """
        body = {
            "identity": identity._asdict(),
            "createRefreshToken": True
        }
        logger.debug(f"Request body: {body}")
        with self.client.post("/sessions", json=body,
                              catch_response=True) as resp:
            logger.debug(f"Response: {resp}")
            if resp.status_code != 200:
                resp.failure("Create failure")
            else:
                js = resp.json()
                return js["sessionToken"]["token"]

    def get(self, token: str):
        with self.client.rename_request("/sessions/{token}"):
            with self.client.get(f"/sessions/{token}") as resp:
                if resp.status_code != 200:
                    resp.failure("Get failure")
