import React, { useEffect, useState } from 'react';

export const GoogleDriveImage: React.FC<{ fileId: string }> = (props) => {
    const [svgContent, setSvgContent] = useState<string>('');
    const [loading, setLoading] = useState<boolean>(true);

    useEffect(() => {
        const fetchImage = async () => {
            const baseUrl: string = `https://drive.google.com/uc?export=view&id=${props.fileId}`;
            try {
                const response = await fetch(baseUrl, { mode: 'no-cors' });
                if (response.ok) {
                    const text = await response.text();
                    setSvgContent(text);
                } else {
                    console.error('Error fetching the SVG:', response.statusText);
                }
            } catch (error) {
                console.error('Error fetching the SVG:', error);
            } finally {
                setLoading(false);
            }
        };

        fetchImage();
    }, [props.fileId]);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            {svgContent ? (
                <div dangerouslySetInnerHTML={{ __html: svgContent }} style={{ width: '100%', height: 'auto' }} />
            ) : (
                <div>Error loading SVG content</div>
            )}
        </div>
    );
};
