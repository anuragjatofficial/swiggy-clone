import { useState } from 'react';
import Select from 'react-select'
function SelectTag() {
    const [value, setValue] = useState(null);
    const options = [
        { value: "JAVA", label: "java" },
        { value: "MERN", label: "mern" },
        { value: "NXM", label: "nxm" },
        { value: "SDET", label: "sdet" }
    ];
    return (
        <div style={{ matgin: 20, width: 500 }}>
        <Select options={options} defaultValue={value} placeholder="Select your course" onChange={setValue} isMulti isSearchable noOptionsMessage={()=>"No coures found"}
            styles={{
                placeholder : (baseStyles, state) => ({
                    ...baseStyles,
                    color : "red",
                    fontSize: 20,
                })
            }}/>
        </div>
    );
}

export { SelectTag }