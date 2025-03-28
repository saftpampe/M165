export const validatePlayer = (player) => {
    const errors = {};

    if (!player.firstName || player.firstName.trim() === '') {
        errors.firstName = 'First name is required';
    }

    if (!player.lastName || player.lastName.trim() === '') {
        errors.lastName = 'Last name is required';
    }

    if (!player.teamId || player.teamId.trim() === '') {
        errors.teamId = 'Team ID is required';
    }

    return errors;
};