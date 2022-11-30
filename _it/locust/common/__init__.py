"""
The generated tokens are maintained in a list with size limit.
"""
import logging
import random
from threading import Lock

generated_tokens = []
SIZE_LIMIT = 1000
token_lock = Lock()

logging.basicConfig(level=logging.DEBUG)
logger = logging.getLogger("tokens")
logger.setLevel(logging.INFO)


def token_append(token: str):
    """
    Append a token to the list.
    If the list is already full, remove the old one and append the latest into.
    :param token:
    :return:
    """
    with token_lock:
        global generated_tokens
        if len(generated_tokens) >= SIZE_LIMIT:
            generated_tokens.pop()
        generated_tokens.append(token)
        logger.debug(f"Append a token, list size: {len(generated_tokens)}")


def token_random_get():
    size = len(generated_tokens)
    if size <= 0:
        raise Exception("No token available")
    pos = random.randrange(0, size)
    return generated_tokens[pos]
